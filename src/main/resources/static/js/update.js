/**
 * 
 */
 'use strict';
 
function updateUserData(){
    // index.htmlの検索IDの値を取得

    
    const id = document.getElementById('updId');
    const name = document.getElementById('updName');
    const age = document.getElementById('updAge');
    const gender = document.getElementById('updGender');
    const address = document.getElementById('updAddress');
    const hobby = document.getElementById('updHobby');
    const introduction = document.getElementById('updIntroduction');


    // AjaxにてDemoControllerクラスのsearchメソッドを呼び出す
    let request = new XMLHttpRequest();
    request.open("get", "/updateInPlace?id&name&age&gender&address&hobby&introduction=" + id&name&age&gender&address&hobby&introduction, true);

    request.send(null);
    request.onload = function (event) {
       // Ajaxが正常終了した場合
       if (request.readyState === 4 && request.status === 200) {
          // 該当するデータが無かった場合
          if(!request.responseText){
              alert("該当するデータはありませんでした");
              return;
          }
       // Ajaxが異常終了した場合
       }else{
          alert("エラーが発生し、データが取得できませんでした");
       }
    };
    // Ajaxが異常終了した場合
    request.onerror = function (event) {
       alert("エラーが発生し、データが取得できませんでした");
    }
}