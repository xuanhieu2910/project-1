package vuonghieu.project.mapper.mapperDto.impl;

import org.springframework.stereotype.Component;
import vuonghieu.project.dto.InventoryDTO;
import vuonghieu.project.entity.Book;

import java.util.*;
@Component
public class InventoryMapperDTO {



    public List<InventoryDTO> inventoryDTOS(List<Book>books){
        List<InventoryDTO>inventoryMapperDTOS = new ArrayList<>();
        for(Book book:books){
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setCodeBookParent(book.getCodeBook());
            inventoryDTO.setNameBook(book.getNameBook());
            inventoryDTO.setAuthor(book.getAuthor());
            inventoryDTO.setCreatedOn(book.getCreatedOn());
            inventoryDTO.setCompany(book.getCompany());
            inventoryDTO.setDescriptionBook(book.getDescription());
            inventoryDTO.setInventory(book.getInventory());
            inventoryDTO.setQuantity(book.getQuantity());
            inventoryDTO.setSales(book.getSales());
            inventoryMapperDTOS.add(inventoryDTO);
        }
        return inventoryMapperDTOS;
    }


    public InventoryDTO convertBookToDTOS(Book book){
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCodeBookParent(book.getCodeBook());
        inventoryDTO.setNameBook(book.getNameBook());
        inventoryDTO.setAuthor(book.getAuthor());
        inventoryDTO.setQuantity(book.getQuantity());
        inventoryDTO.setSales(book.getSales());
        inventoryDTO.setInventory(book.getInventory());
        return inventoryDTO;
    }
}
