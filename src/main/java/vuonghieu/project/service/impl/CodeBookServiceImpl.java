package vuonghieu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vuonghieu.project.dao.impl.CodeBookDaoImpl;
import vuonghieu.project.entity.Book;
import vuonghieu.project.entity.CodeBook;
import vuonghieu.project.service.RootService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CodeBookServiceImpl {
    //Auto gen code follow code parent;
    //key auto gen increte . No encode.

    @Autowired
    CodeBookDaoImpl codeBookDao;


    public void save(Book parentBook, int quantity) {
        String genCodeChild = parentBook.getCodeCategory()+"-"+parentBook.getCodeMajor()+"-"+parentBook.getCodeBook()+"-";
        for(int i=1;i<=quantity;i++) {
            CodeBook  codeBook = new CodeBook();
            codeBook.setCodeBookChild(genCodeChild+i);
            codeBook.setCodeBook(parentBook.getCodeBook());
            codeBookDao.save(codeBook);
        }
    }


    public List<CodeBook> findAll() {
        return null;
    }

    public void updateCodeBookBorrow(List<String> codeBookChildren){
        for(String codeBookChild:codeBookChildren) {
            codeBookDao.updateCodeBookBorrow(codeBookChild);
        }
    }

    public void updateCodeBookPaymentBorrow(List<String>codeBookChildren){
        for(String codeBookChild: codeBookChildren){
            codeBookDao.updateCodeBookPaymentBorrow(codeBookChild);
        }
    }


    public String findByStringCodeBookChild(String codeBookChild) {
        return codeBookDao.getCodeBookByCodeBookChild(codeBookChild);
    }


    public CodeBook updateById(CodeBook codeBook, int id) {
        return null;
    }

    public void deleteCodeBookChildByCodeBookParent(String codeBookParent) {
        codeBookDao.deleteCodeBookChildByCodeBookParent(codeBookParent);
    }

    public int getCountByCodeBook(String codeBookParent){
        return codeBookDao.getCountByCodeBook(codeBookParent);
    }

    public  List<String> getCodeBookByCodeChild(List<String> codeChild){
        List<String>codeBookParent = new ArrayList<>();
        for(int i=0;i<codeChild.size();i++){
            codeBookParent.add(codeBookDao.getCodeBookByCodeBookChild(codeChild.get(i)));
        }
        return codeBookParent;
    }

    public void updateCodeBook(String newCodeBook,String oldCodeBook){
        codeBookDao.updateOldCodeBook(oldCodeBook);
        codeBookDao.updateNewCodeBook(newCodeBook);
    }

    public void updateCodeBook(String codeBook){
        codeBookDao.updateOldCodeBook(codeBook);
    }
}
