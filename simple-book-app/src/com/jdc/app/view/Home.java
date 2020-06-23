package com.jdc.app.view;
import java.util.ArrayList;
import java.util.List;

import com.jdc.app.entity.Author;
import com.jdc.app.entity.Book;
import com.jdc.app.entity.Category;
import com.jdc.app.entity.SaleDetail;
import com.jdc.app.service.BookService;
import com.jdc.app.service.CategoryService;
import com.sun.tools.javac.util.Convert;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Home {
	
	@FXML
	private ComboBox<Category> category;
	@FXML
	private ComboBox<Author> authorName;
	@FXML
	private TextField bookName;
	@FXML
	private DatePicker releasedDate;
	@FXML
	private TableView<Book> tblList;
	@FXML
	private TableView<SaleDetail> tblCart;
	@FXML
	private Label lblSubTotal;
	@FXML
	private Label lblTotal;
	private CategoryService catService;
	private BookService bookService;
	List<SaleDetail> cartList;	
	public void addToCart(MouseEvent event) {			
		cartList=new ArrayList<SaleDetail>();	
		cartList=tblCart.getItems();		
		List<SaleDetail> temp=new ArrayList<SaleDetail>();
		int status=0;
		int lblSubtotalVal=0;
		if(event.getClickCount() == 2) {			
			Book book = tblList.getSelectionModel().getSelectedItem();	
			for (SaleDetail b : cartList) {				
			    if (b.getBookName().equals(book.getName())) {
			       b.setQuantity(b.getQuantity()+1);	
			       status=1;
			         }
			    lblSubtotalVal+=b.getSubTotal();
			    temp.add(b);			    
			}
			cartList=temp;			
			   if (status==0)
				   {
				   	SaleDetail sd= new SaleDetail();
					sd.setBookName(book.getName());
					sd.setQuantity(1);
					sd.setUnitPrice(book.getPrice());
					sd.setSubTotal(sd.getSubTotal());
					lblSubtotalVal+=sd.getSubTotal();
					cartList.add(sd);
				   }	
			   	tblCart.getItems().clear();				   
 			    tblCart.getItems().addAll(cartList);
 			    lblSubTotal.setText(Integer.toString(lblSubtotalVal));
 			    
					}
				}
		public void search() {
		tblList.getItems().clear();		
		List<Book> bookList = bookService.findByParamsForHome(category.getValue(),bookName.getText().toString());
		tblList.getItems().addAll(bookList);
		}
	private void loadCategory() {
		List<Category> catList = catService.findAll();
		category.getItems().addAll(catList);
	}
	public void clear() {
	  	tblCart.getItems().clear();		
	  	lblSubTotal.setText("");
	}
	
	@FXML
	private void initialize() {
	catService = CategoryService.getInstance();	
	bookService = BookService.getInstance();
	loadCategory();
	search();		
	category.valueProperty().addListener((a, b, c) -> search());
	
	}

}