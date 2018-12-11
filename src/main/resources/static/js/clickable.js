

// see http://jsfiddle.net/ionutgg/der8awok/

$(".clickable").click(function (e) {
    document.addEventListener('click', function (e) {
        console.log('Is Alt/option key pressed?', e.altKey)

        if (e.altKey === true) {
            s = window.getSelection();
            var range = s.getRangeAt(0);
            var node = s.anchorNode;

            // find start  of word
            while (range.toString().search(/\W/) != 0 && range.startOffset != 0) {
                range.setStart(node, (range.startOffset - 1));
            }
            // correct for word at start of string
            if ( range.startOffset > 0 || range.toString().search(/\W/) >= 0) {
                range.setStart(node, range.startOffset + 1);
            }

            // find end of word
            while (range.toString().search(/\W/) < 0 && range.endOffset < node.length) {
                range.setEnd(node, range.endOffset + 1);
            }
            // correct for word at end of string
            if (range.endOffset < node.length || range.toString().search(/\W/) >= 0) {
                range.setEnd(node, range.endOffset - 1);
            }

//            var parsedUrl = $.url(window.location.href);
//            var params = parsedUrl.param();
//            delete params["page"];
//            params["search"] = range.toString().trim();
//            var newParams = "?" + $.param(params);
//
//            var str = newParams;
//            window.location.href = str;

            search(range.toString().trim());
        }
    })

});
