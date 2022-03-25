/**
 * その場編集
 */

	(function(document){
	    
	    $(document).ready(function(){
	    	 $("#userlisttable > tbody > tr > td").click(edit_toggle());
	    });


	    function edit_toggle(){
	        var edit_flag=false;
	        return function(){
	            if(edit_flag) return;
	            var $input = $("<input>").attr("type","text").val($(this).text());
	            $(this).html($input); 
	            
	            $("input", this).focus().blur(function(){
	                save(this);
	                $(this).after($(this).val()).unbind().remove();
	                edit_flag = false;
	            });
	            edit_flag = true;
	        }
	    }    
	    
	    function save(){
		
	    }
	})(document);