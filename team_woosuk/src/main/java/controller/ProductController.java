package controller;

import java.io.IOException;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import product.Product;
import product.ProductDao;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;

@WebServlet(urlPatterns = { "/product/*" }, initParams = { @WebInitParam(name = "view", value = "/view/product/"), // jsp 위치
		 @WebInitParam(name = "login", value = "login")}) 


	public class ProductController extends MskimRequestMapping {
		
		@RequestMapping("index")
		public String index(HttpServletRequest request, HttpServletResponse response) {
			
			return "index";
		} // index Test
		
		@RequestMapping("productForm")
		public String productForm(HttpServletRequest request, HttpServletResponse response) {

			return "productForm";
		}
		
		@RequestMapping("productPro")
		public String productPro(HttpServletRequest request, HttpServletResponse response) {
			String path = request.getServletContext().getRealPath("/")+"view/product/images"; // 사진 파일 경로
			String msg = "상품 등록에 실패하였습니다.";
			String url = "/product/productForm";
			
			
			
			String filename="";
			try {
				MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "UTF-8");
				filename = multi.getFilesystemName("image");
				Product product = new Product();
				
				
				product.setName(multi.getParameter("name"));
				product.setPrice(Integer.parseInt(multi.getParameter("price")));
				product.setStock(Integer.parseInt(multi.getParameter("stock")));
				product.setInfo(multi.getParameter("info"));
				product.setImage(filename);
				product.setProdgender(Integer.parseInt(multi.getParameter("prodgender")));
				product.setProdans1(multi.getParameter("prodans1"));
				product.setProdans2(multi.getParameter("prodans2"));
				
				ProductDao pd = new ProductDao();
				
				int num = pd.insertProduct(product);
				if (num > 0) {
					msg = "상품이 등록되었습니다.";
					url = "/product/productList";
				}
			} catch (IOException e) {
				e.printStackTrace(); // 오류 메시지 콘솔에 출력
			}
			
			request.setAttribute("msg", filename+": "+msg);
			request.setAttribute("url", url);
			return "alert";
		} // productForm End
		
		@RequestMapping("productList")
		public String productList(HttpServletRequest request, HttpServletResponse response) {
			
			return "productList";
		} // boardList End
		
	} // ProductController End

