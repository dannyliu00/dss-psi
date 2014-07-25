(function() {
	var commentDisplay = sellInNamespace('sellIn.directives.commentdisplay');

    angular.module('sellIn.directives.commentdisplay', [])
        .controller('commentDisplayController', commentDisplay.CommentDisplayController)
        .directive('commentDisplay', commentDisplay.CommentDisplayDirective);
})();