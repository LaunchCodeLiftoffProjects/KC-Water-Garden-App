function showMoreText() {
        var showChar = 75;
        var ellipsestext = "...";
        var moretext = "Show more >";
        var lesstext = "Show less";

    $('.show-read-more').each(function() {
        var content = $(this).html();

        if(content.length > showChar) {
            var c = content.substr(0, showChar);
            var end = Math.min(c.length, c.lastIndexOf(" "));
            c = c.substr(0, end);
            var h = content.substr(end, content.length - end);
            var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent" ><span>' + h + '</span>&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';
            $(this).html(html);
        }

    });

    $(".morelink").click(function(){
    if($(this).hasClass("more")) {
        $(this).removeClass("more");
        $(this).html(moretext);
    } else {
        $(this).addClass("more");
        $(this).html(lesstext);
    }
        $(this).parent().prev().toggle();
        $(this).prev().toggle();
        return false;
    });
}

$(document).ready(showMoreText);

