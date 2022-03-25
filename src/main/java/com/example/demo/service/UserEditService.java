package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserObj;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserEditService {
	  /**
	   * ユーザー情報 Repository
	   */
	  @Autowired
	  private UserRepository userRepository;
	  /**
	   * ユーザー情報 全検索
	   * @return 検索結果
	   */
	  public List<User> searchAll() {
	    return userRepository.findAll();
	  }
	  /**
	   * ユーザー情報 主キー検索
	   * @return 検索結果
	   */
	  public User findById(Long id) {
	    return userRepository.findById(id).get();
	  }
	  /**
	   * ユーザー情報 更新
	   * @param user ユーザー情報
	   */
	  public void update(UserUpdateRequest userUpdateRequest) {
	    User user = findById(userUpdateRequest.getId());
	    
    	String getHobby = userUpdateRequest.getHobby();
    	if(getHobby.contains(",")){
    		getHobby = getHobby.replace(",", "、");
    	}
	    
	    user.setName(userUpdateRequest.getName());
	    user.setAge(userUpdateRequest.getAge());
	    user.setGender(userUpdateRequest.getGender());
	    user.setAddress(userUpdateRequest.getAddress());
	    user.setHobby(getHobby);
	    user.setIntroduction(userUpdateRequest.getIntroduction());
	    user.setUpdateDate(new Date());
	    userRepository.save(user);
	  }
	  
	  /**
	   * ユーザー情報 物理削除
	   * @param id ユーザーID
	   */
	  public void delete(Long id) {
	    User user = findById(id);
	    userRepository.delete(user);
	  }
	  
	  //PDFダウンロード
	  public void pdfDownload(Long id) throws JRException {
		//jasperファイルのパス取得
		  String jasperFilePath =  getClass().getResource("/jasper/UserData.jasper").getPath();
		  
		  User user= findById(id);

		  //帳票データ作成
		  UserObj te01 = new UserObj(user.getName(), user.getAge(), user.getGender(), user.getAddress(), user.getHobby(), user.getIntroduction());
		  
		  List<UserObj> list= new ArrayList<UserObj>();
		  list.add(te01);

		  //Collectionデータソースの設定
		  JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);

		  //parameterの設定        
		  HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		  parameterMap.put("pdfName", te01.getPdfName());
		  parameterMap.put("pdfAge", te01.getPdfAge());
		  parameterMap.put("pdfGender", te01.getPdfGender());
		  parameterMap.put("pdfAddress", te01.getPdfAddress());
		  parameterMap.put("pdfHobby", te01.getPdfHobby());
		  parameterMap.put("pdfIntro", te01.getPdfIntro());

		  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, parameterMap, jrBeanCollectionDataSource);
		  JasperExportManager.exportReportToPdfFile(jasperPrint, "UserData.pdf");
	  }
}
