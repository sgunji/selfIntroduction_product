/**
 * 
 */
 function clickUserData(){
 
	jQuery("#btn_javascript_sample_table_1").click(function() {
		var tblTbody = document.getElementById('javascript_sample_table_1_tbody');
		document.getElementById("txtArea_javascript_sample_table_1").value = "";
		// td内のtrをループ。rowsコレクションで,行位置取得。
		for (var i=0, rowLen=tblTbody.rows.length; i<rowLen; i++) {
			// tr内のtdをループ。cellsコレクションで行内セル位置取得。
			for (var j=0, colLen=tblTbody.rows[i].cells.length ; j<colLen; j++) {
				//i行目のj列目のセルを取得
				var cells = tblTbody.rows[i].cells[j];
				//取得した値をテキストエリアへ表示
				document.getElementById("txtArea_javascript_sample_table_1").value += i + "行目" + j + "列目の値は「" + cells.innerHTML + "」です。\n";

			}
		}
	});
}