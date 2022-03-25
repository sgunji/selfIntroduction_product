function checkInput(){

	var errorMessages = [];
    //名前の未入力チェック
    let nameElem = "";
    nameElem = document.getElementById('name');
    if(nameElem.value === ''){
        errorMessages.push('名前を入力してください。');
    //名前の全角入力チェック
    } else if(!nameElem.value.match(/^[^\x01-\x7E\xA1-\xDF]+$/)){
        errorMessages.push('名前は全角で入力してください。')
    }

     //年齢の未入力チェック
    let ageElem = document.getElementById('age');
    if(ageElem.value === ''){
        errorMessages.push('年齢を入力してください。');
    }
    
    //住所の未入力チェック
    let addressElem = document.getElementById('address');
    if(addressElem.value === ''){
        errorMessages.push('住所を入力してください。');
    }
    
    // エラーメッセージがなければOK！
    if (errorMessages.length === 0) {
      return true;
    } else {    
	// エラーメッセージを表示して false を返す
    alert(errorMessages.join('\n'));
    return false;
    }
 }