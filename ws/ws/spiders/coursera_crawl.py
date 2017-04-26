import scrapy
import MySQLdb
import re
import unicodedata

class QuotesSpider(scrapy.Spider):
    name = "cera"

    start_urls = [
        'https://www.coursera.org/courses?languages=en&query=&start=0',
    ]
    __ka=0
    def parse(self, response):
        self.__ka = self.__ka+20 
        #start
        

        
        if self.__ka == 0:
            start =0
        else:
            start = self.__ka

        
        db = MySQLdb.connect("localhost","root","","ws_pbl" )
        cursor = db.cursor()
        rawfile = []
        spll = ''
        linkk = ''
        university_name = ''
        course_name = ''
        rawfile= response.xpath('//*[@id="rendered-content"]/div/div/div[2]/div[1]/div[2]/div[1]/div').extract()
        for i in range(2,22):
            spl = re.findall(r'\d{1,2}\-[a-z]*\s[A-Z][a-z]*',rawfile[i])
            
            if len(spl)==0:
                spll = " "
            else:
                spll = linkk= unicodedata.normalize('NFKD',spl.pop()).encode('ascii','ignore')
            print spll

            link = re.findall(r'href="(/[a-zA-Z/]*)',rawfile[i])
           # link1 = re.findall(r'/[a-z0-9//]*',link[0])
            if len(link) == 0 :
                print 'empty hahhh'
            else:

                linkk= unicodedata.normalize('NFKD',link.pop()).encode('ascii','ignore')
                linkk = "https://www.coursera.org"+link[0]
            print linkk
            univ = re.findall(r'<span\sdata-reactid="\d*">\D[A-Za-z\s,]*',rawfile[i])

            uname=re.findall(r'>([a-zA-Z0-9:\.\s,]*)',str(univ))
            
            if len(uname)==0:
                print 'empty uname hahha'
            else:

                university_name = uname[0]
            print university_name
            match = re.findall(r'class="color-[a-zA-z0-9\-\s]*"[\s"\-a-zA-Z0-9=]*\s[a-z0-9\-="]*>[a-zA-Z0-9:\.\s]*',rawfile[i])
            cname = re.findall(r'>([a-zA-Z0-9:\.\s]*)',str(match))
            if len(cname)==0:
                print 'empty cname haha'
            else:

                course_name = cname[0]
            print course_name
            print '________________________________________________'
            print start
            
            cursor.execute("INSERT INTO courses(name,unvi,link,spl,website)VALUES ('%s','%s','%s','%s','coursera')" %(course_name,university_name,linkk,spll))
            db.commit()
            

                
                
        start = start +20
        next_page = 'courses?language=en&query=&start=%d'%start
        print 'nextpage is'+next_page
        if start != 1780:
            next_page = response.urljoin(next_page)
            yield scrapy.Request(next_page, callback=self.parse)
        db.close()