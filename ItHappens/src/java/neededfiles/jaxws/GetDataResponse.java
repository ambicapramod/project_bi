
package neededfiles.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getDataResponse", namespace = "http://neededfiles/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDataResponse", namespace = "http://neededfiles/")
public class GetDataResponse {

    @XmlElement(name = "return", namespace = "")
    private List<neededfiles.Courses> _return;

    /**
     * 
     * @return
     *     returns List<Courses>
     */
    public List<neededfiles.Courses> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<neededfiles.Courses> _return) {
        this._return = _return;
    }

}
