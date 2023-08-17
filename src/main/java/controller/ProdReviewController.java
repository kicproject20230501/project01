package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.ProdReview;
import service.ProdReviewMybatis;

@RestController
@RequestMapping("/prodReview/")
public class ProdReviewController {
	
	@Autowired
	ProdReviewMybatis prd;
	
	@PostMapping("enroll")
	public void enrollReplyPOST(ProdReview prodReview) {
		prd.insertProdReview(prodReview);
	}
	
	
}
