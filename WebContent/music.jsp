<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MUSIC🎵</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.10.0/jquery.min.js"></script>
	<style type="text/css">
		body{
		        background:#2b2938;
		    }
		    .btn-audio{
		        margin: 90px auto;
		        width: 36px;
		        height: 36px;
		        background:url(images/voice_stop.png) no-repeat center bottom;
		        background-size:cover;
		    }
	</style>
	
<script type="text/javascript">
        $(function(){
            //播放完毕
            $('#mp3Btn').on('ended', function() {
                console.log("音频已播放完成");
                $('.btn-audio').css({'background':'url(images/voice_stop.png) no-repeat center bottom','background-size':'cover'});
            })
            //播放器控制
            var audio = document.getElementById('mp3Btn');
            audio.volume = .3;
            $('.btn-audio').click(function() {
                event.stopPropagation();//防止冒泡
                if(audio.paused){ //如果当前是暂停状态
                    $('.btn-audio').css({'background':'url(images/voice_play.png) no-repeat center bottom','background-size':'cover'});
                    audio.play(); //播放
                    return;
                }else{//当前是播放状态
                    $('.btn-audio').css({'background':'url(images/voice_stop.png) no-repeat center bottom','background-size':'cover'});
                    audio.pause(); //暂停
                }
            });
        })
</script>
</head>
<body>
<!-- 	<audio src="songs/001.mp3" controls="conctrols">
		Your browser does not support the audio tag.
	</audio> -->

    <div class="btn-audio">
    	<audio id="mp3Btn" src="songs/001.mp3" type="audio/mpeg" /></audio>
    </div>
</body>
</html>