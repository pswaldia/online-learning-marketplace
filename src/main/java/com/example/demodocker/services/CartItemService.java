package com.example.demodocker.services;

import com.example.demodocker.dto.CartViewResponse;
import com.example.demodocker.entity.CartItem;
import com.example.demodocker.entity.Course;
import com.example.demodocker.entity.User;
import com.example.demodocker.exceptions.ProductAlreadyInTheCartException;
import com.example.demodocker.repositories.CartItemRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    public CartItem addCourse(Integer courseId, Long userid){
        Course course = courseService.getById(courseId);
        User user = userService.getUserById(userid);
        CartItem cartItem = cartItemRepository.findByCourseAndUser(course, user);
        if(cartItem != null){  // course associated with given user is already in cart.
            throw new ProductAlreadyInTheCartException("Course already present in the cart.");
        }
        CartItem cartItem1 = new CartItem();
        cartItem1.setCourse(course);
        cartItem1.setUser(user);
        return cartItemRepository.save(cartItem1);
    }

    public List<CartItem> listCartItemsByUser(User user){
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        return cartItems;
    }
    public void removeCourse(Integer courseId, Long userId){
        Course course = courseService.getById(courseId);
        User user = userService.getUserById(userId);
        System.out.println(course);
        System.out.println(user);
        CartItem cartItem = cartItemRepository.findByCourseAndUser(course, user);
        cartItemRepository.delete(cartItem);
    }

    public ByteArrayInputStream generatePdfReport(float total, List<CartViewResponse> finalCart) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{3,3,2,2});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell = new PdfPCell(new Phrase("Course Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Discount %", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Net Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for(CartViewResponse item : finalCart){
                PdfPCell cell = new PdfPCell(new Phrase(item.getCourseName()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase( Float.toString(item.getPrice())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Float.toString(item.getDiscountPercentage())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(
                        Float.toString(item.getPrice() - (item.getDiscountPercentage()*item.getPrice())/100)
                ));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            Logger.getLogger(CartItemService.class.getName()).log(Level.SEVERE, null, e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public void deleteByUser(User user) {
        cartItemRepository.deleteAllByUser(user);
    }
}
