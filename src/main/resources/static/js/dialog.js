/**
 * 確認ダイアログ
 */
 
 const REGISTER_MESSAGE = "登録してもよろしいですか？"
$('.register-action').click(function() {
	if(!confirm(REGISTER_MESSAGE)){
		return false;
	}
});

 const EDIT_MESSAGE = "編集してもよろしいですか？"
$('.edit-action').click(function() {
	if(!confirm(EDIT_MESSAGE)){
		return false;
	}
});

 const DELETE_MESSAGE = "削除してもよろしいですか？"
$('.delete-action').click(function() {
	if(!confirm(DELETE_MESSAGE)){
		return false;
	}
});