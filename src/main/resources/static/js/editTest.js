/**
 * 
 */
function checkUserData(){
	 const UPDATE_MESSAGE = "更新してもよろしいですか？"
	if(!confirm(UPDATE_MESSAGE)){
		return false;
	}else{
    // idでhtmlからtableの要素を取得し、trタグのNodeListを取得
    let table = document.getElementById('userlisttable');
    let tbl_tr = table.querySelectorAll('tr');
    
    let json = {
	"id":"",
    "name": "",
    "age":  "",
    "gender": "",
    "address": "",
    "hobby": "",
    "introduction": "",
    "update_date": "",
    "create_date": ""
     }
    //テーブルの1行(trタグ)毎に処理
    tbl_tr.forEach(function(tr){
	    
        //tdタグのNodeListを取得
        let cells = tr.querySelectorAll('td');
        //テーブルのヘッダー部分は飛ばす
        if (cells.length!=0){
	
            // テーブルの1行(trタグ)のデータを格納する配列
            let d =[];
            //セル(tdタグ)毎に処理
            cells.forEach(function(td){
                //セルがinputタグだった場合
                if(td.innerHTML.indexOf('input') != -1) {
                    d.push(td.firstElementChild.value);
                }
                //セルがtextだった場合
                else if(td.textContent!=""){
                    d.push(td.textContent);
                }
                //セルが空白だった場合
                else{
                    d.push("");
                }
                json.id = d[0];
                json.name = d[1];
                json.age = d[2];
                json.gender = d[3];
                json.address = d[4];
                json.hobby = d[5];
                json.introduction = d[6];
                json.update_date = d[7];
                json.create_date = d[8];
                
            });
            
          $.ajax({
            type:'POST',
            url:'updateInPlace',
            data: JSON.stringify(json),
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content'),
               },
             contentType: 'application/json',
             dataType:'json',
            acriptCharset:'utf-8'
        }).done(function(dataJson) {
        	console.log(dataJson);
          })
          .fail(function(XMLHttpRequest, textStatus, errorThrown, dataJson) {
                alert('error!!!');
                console.log("XMLHttpRequest : " + XMLHttpRequest.status);
                console.log("textStatus     : " + textStatus);
                console.log("errorThrown    : " + errorThrown.message);
                console.log(dataJson);
          })

        }
    });
   setTimeout(function(){
      window.location.href = 'http://localhost:8080/user/userList';
     }, 0.5*1000);
 }
 }
