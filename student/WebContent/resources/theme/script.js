function checkboxlimit(checkgroup){
	for (var i=0; i<checkgroup.length; i++){
		checkgroup[i].onclick=function(){
			for (var j=0; j<checkgroup.length; j++){
				checkgroup[j].checked = false;
			}
			this.checked = true;
		}
	}
}


