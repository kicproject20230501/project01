package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.Product;
import service.ProductMybatis;

@Controller
@RequestMapping("/product/")
public class ProductController {
	
	@Autowired
	ProductMybatis pd;
	
	Model m;
	HttpSession session;
	HttpServletRequest request;
	
	//초기화 작업을 한다, 객체 초기화시에 사용한다 
	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request=request;
		this.m=m;
		session = request.getSession();
		
	}
	
	// 상품 입력 페이지
	@RequestMapping("productForm")
	public String productForm() {

		return "product/productForm";
	} // productForm End
	
	// 상품 업로드
	@RequestMapping("productPro")
	public String productPro(@RequestParam("f1") MultipartFile multipartFile1,
			@RequestParam("f2") MultipartFile multipartFile2,
			Product product) {
		String path = request.getServletContext().getRealPath("/") + "WEB-INF/view/product/images"; // 사진 파일 경로
		String msg = "상품 등록에 실패하였습니다.";
		String url = "product/productForm";

		String filename1 = " ";
		String filename2 = " ";
		
		if (!multipartFile1.isEmpty() && !multipartFile2.isEmpty()) {
			File file1 = new File(path, multipartFile1.getOriginalFilename()); // 상품 이미지
			File file2 = new File(path, multipartFile2.getOriginalFilename()); // 상세 이미지
			
			filename1 = multipartFile1.getOriginalFilename();
			filename2 = multipartFile2.getOriginalFilename();
			try {
				multipartFile1.transferTo(file1);
				multipartFile2.transferTo(file2);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		product.setImage(filename1);
		product.setDetail(filename2);

		int num = pd.insertProduct(product);
		if (num > 0) {
			msg = "상품이 등록되었습니다.";
			url = "/product/productManagement";
		}
		

		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	} // productPro End
	
	// 상품 페이지
	@RequestMapping("shop")
	public String productList() {
		session.setAttribute("pageNum", "1");
		if (request.getParameter("pageNum") != null) /* pageNum을 넘겨 받음 */ {
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}
		String pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null)
			pageNum = "1"; // 넘겨받은 pageNum이 없으면 1페이지로
		

		int limit = 6; // 한 page 당 게시물 갯수
		int pageInt = Integer.parseInt(pageNum); // page 번호
		int productCount = pd.productCount(); // 전체 게시물 갯수
		int prodNum = productCount - ((pageInt - 1) * limit);

		List<Product> li = pd.productList(pageInt, limit);

		int bottomLine = 3;
		int start = (pageInt - 1) / bottomLine * bottomLine + 1;

		int end = start + bottomLine - 1;
		int maxPage = (productCount / limit) + (productCount % limit == 0 ? 0 : 1);
		if (end > maxPage)
			end = maxPage;

		// 주문 관련 추가한 코드

		m.addAttribute("li", li);
		m.addAttribute("prodNum", prodNum);

		m.addAttribute("pageInt", pageInt);
		m.addAttribute("bottomLine", bottomLine);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("maxPage", maxPage);

		return "product/shop";
	} // productList End
	
	// 상품 관리 페이지 (admin 전용)
	@RequestMapping("productManagement")
	public String productManagement() {
		session.setAttribute("pageNum", "1");
		if (request.getParameter("pageNum") != null) /* pageNum을 넘겨 받음 */ {
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}
		String pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null)
			pageNum = "1"; // 넘겨받은 pageNum이 없으면 1페이지로
		

		int limit = 6; // 한 page 당 게시물 갯수
		int pageInt = Integer.parseInt(pageNum); // page 번호
		int productCount = pd.productCount(); // 전체 게시물 갯수
		int prodNum = productCount - ((pageInt - 1) * limit);

		List<Product> ma = pd.productList(pageInt, limit);

		int bottomLine = 5;
		int start = (pageInt - 1) / bottomLine * bottomLine + 1;

		int end = start + bottomLine - 1;
		int maxPage = (productCount / limit) + (productCount % limit == 0 ? 0 : 1);
		if (end > maxPage)
			end = maxPage;

		m.addAttribute("prodNum", prodNum);
		m.addAttribute("pageInt", pageInt);
		m.addAttribute("bottomLine", bottomLine);
		m.addAttribute("start", start);
		m.addAttribute("end", end);
		m.addAttribute("maxPage", maxPage);
		m.addAttribute("ma", ma);
		return "product/productManagement";
	} // productManagement End
	
	// 상품 정보 수정 페이지 (admin 전용)
	@RequestMapping("productUpdateForm")
	public String productUpdateForm(@RequestParam("prodnum") int prodnum) {

		Product product = pd.productOne(prodnum);

		m.addAttribute("product", product);
		return "product/productUpdateForm";
	} // productUpdateForm End
	
	// 상품 정보 수정 (admin 전용)
	@RequestMapping("productUpdatePro")
	public String productUpdatePro(@RequestParam("f1") MultipartFile multipartFile1,
			@RequestParam("f2") MultipartFile multipartFile2,
			@RequestParam("prodnum") int prodnum,
			Product product) {
		String path = request.getServletContext().getRealPath("/") + "WEB-INF/view/product/images"; // 사진 파일 경로
		String msg = "";
		String url = "";
		
		Product productOne = pd.productOne(prodnum);
		
		String filename1 = productOne.getImage();
		String filename2 = productOne.getDetail();
		
		if (!multipartFile1.isEmpty()) {
			File file1 = new File(path, multipartFile1.getOriginalFilename()); // 상품 이미지
			filename1 = multipartFile1.getOriginalFilename();
			try {
				multipartFile1.transferTo(file1);
				product.setImage(filename1);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!multipartFile2.isEmpty()) {
			File file2 = new File(path, multipartFile2.getOriginalFilename()); // 상세 이미지
			filename2 = multipartFile2.getOriginalFilename();
			try {
				multipartFile2.transferTo(file2);
				product.setDetail(filename2);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		if (pd.productUpdate(product) > 0) /* Update OK */ {
			msg = "수정을 완료했습니다.";
			url = "/product/productManagement"; 
		} else { // update fail
			msg = "수정을 실패했습니다.";
			url = "/product/productUpdateForm?prodnum=" + product.getProdnum(); // 해당 게시물의 UpdateForm으로 이동
		}
			
		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	} // productUpdatePro End
	
	// 상품 삭제 페이지 (admin 전용)
	@RequestMapping("productDeleteForm")
	public String productDeleteForm(@RequestParam("prodnum") int prodnum) {

		m.addAttribute("prodnum", prodnum);
		return "product/productDeleteForm";
	} // productDeleteForm End
	
	// 상품 삭제 (admin 전용)
	@RequestMapping("productDeletePro")
	public String productDeletePro(@RequestParam("prodnum") int prodnum) {

		String msg = "";
		String url = "";
		if (pd.productDelete(prodnum) > 0) {
			msg = "상품이 삭제 되었습니다.";
			url = "/product/productManagement";
		} else {
			msg = "상품 삭제에 실패했습니다.";
			url = "/product/productManagement";
		}

		m.addAttribute("msg", msg);
		m.addAttribute("url", url);
		return "alert";
	} // productDeletePro End
	
	// 상품 상세 페이지
	@RequestMapping("productDetail")
	public String productDetail(@RequestParam("prodnum") int prodnum) {

		Product product = pd.productOne(prodnum);

		m.addAttribute("product", product);
		return "product/productDetail";
	} // productDetail End
	

} // ProductController End