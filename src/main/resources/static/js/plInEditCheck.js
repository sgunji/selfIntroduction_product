/**
 * その場編集時入力チェック 
 */
$('#updName').focusout(function(e) {
    //名前の未入力チェック
    let nameElem = "";
    nameElem = document.getElementById('#updName');
    if(nameElem.value === ''){
        alert('名前を入力してください。');
    }
  });