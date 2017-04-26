import scrapy
import MySQLdb
import re
import unicodedata

class QuotesSpider(scrapy.Spider):
    name = "edx"

    start_urls = [
        'https://www.edx.org/api/v1/catalog/search?page=4',
    ]
    __ka=1
    def parse(self, response):
        self.__ka = self.__ka+1 
        #start
        

        
        if self.__ka == 1:
            start =1
        else:
            start = self.__ka

        
        db = MySQLdb.connect("localhost","root","","ws_pbl" )
        cursor = db.cursor()
        rawdata= response.text
        title = re.findall(r'title":"([a-zA-Z\s]*)',rawdata)
        org = re.findall(r'org":"([a-zA-Z\s]*)',rawdata)
        urls = re.findall(r'marketing_url":"([-\:a-zA-Z\s\\.0-9\\/]*)',rawdata)
        pace = re.findall(r'pacing_type":"([-\:a-zA-Z\s\\.0-9\\/_]*)',rawdata)
        if len(title) == 0:
            title = []
        else:
            title_s = [x.encode('UTF8') for x in title]
            print title_s
       
        if len(org) == 0:
            org = []
        else:
            org_s = [x.encode('UTF8') for x in org]
            print org_s

       
        if len(urls) == 0:
            urls = []
        else:
            url_s = [x.encode('UTF8') for x in urls]
            print url_s
       
        if len(pace) == 0:
            pace = []
        else:
            pace_s = [x.encode('UTF8') for x in pace]
            print url_s

        for i in range(0,min(len(urls),len(pace),len(org),len(title))):
            cursor.execute("INSERT INTO courses(name,unvi,link,spl,website)VALUES ('%s','%s','%s','%s','edx')" %(title_s[i],org_s[i],url_s[i],pace[i]))
            db.commit()
        

                
                
        start = start +1
        next_page = 'search?page=%d'%start
        print 'nextpage is'+next_page
        if start != 100:
            next_page = response.urljoin(next_page)
            yield scrapy.Request(next_page, callback=self.parse)
        db.close()