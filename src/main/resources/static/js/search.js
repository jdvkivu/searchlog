
function handle(e){
	s=document.getElementById("search").value;
    if(e.keyCode === 13){
		//write your specific code from here
    	search(s)
    }
	return false;
}

function search(s) {
    var parsedUrl = $.url(window.location.href);
    var params = parsedUrl.param();
    delete params["page"];
    params["search"] = s.trim();
    var newParams = "?" + $.param(params);

    var str = newParams;
    window.location.href = str;
}