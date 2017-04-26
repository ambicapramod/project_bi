/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neededfiles;

/**
 *
 * @author PR-PC
 */
import javax.jws.WebService;
import java.util.*;
@WebService
public interface CalWebService {
    int add(int a,int b);
    List<Courses> getData(String cname);
}