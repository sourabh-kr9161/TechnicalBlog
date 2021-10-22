
//add like js

function doLike(pid,uid)
{
    console.log( pid + " , " + uid );
    var d={
       // pid: pid,
        uid: uid,
        pid: pid,
        operation:'like'
    };
    $.ajax({
        url: "LikeServlet",
        data: d,
        success: function(data,textStatus,jqXHR){
            console.log(data);
            if(data.trim()=='true')
            {
                var c = $(".like-counter").html();
                c++;
                $('.like-counter').html(c);
            }
            
        },
        error: function (jqXHR, textStatus, errorThrown)
        {
            console.log(data);
        }
    });
    
}


	


//add comment js


function addComment(postId, postPubId) 
{
  console.log(postId);
  var comment = document.getElementById(postId+"comment");
  var commentcontent = comment.value;
  var url = "AddComment?postId="+postId+"&postPublisherId="+postPubId+"&commentParentId="+comtParId+"&commentcontent="+commentcontent;
  var req = new XMLHttpRequest();

  try {

      req.onreadystatechange = function() {
      if(req.readyState == 4) {
        comment.value = "";
      }
    };

    req.open("POST",url,true);
    req.send();
  } catch(err) {
    console.log(err);
  }
}





var comment = function(postId, postPubId, comtParId) {
	var comment = document.getElementById(postId+"addComment");
	var commentcontent = comment.value;
	var url = "addcomment?postId="+postId+"&postPublisherId="+postPubId+"&commentParentId="+comtParId+"&commentcontent="+commentcontent;
	var request = new XMLHttpRequest();
	try {
		request.onreadystatechange = function() {
			if(request.readyState == 4) {
				comment.value = "";
			}
		};
		request.open("POST", url, true);
		request.send();
	} catch(err) {
		console.log(err); 
	}
}