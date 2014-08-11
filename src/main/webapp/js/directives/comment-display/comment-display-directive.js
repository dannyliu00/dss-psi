(function () {
    var commentDisplay = sellInNamespace('sellIn.directives.commentdisplay');

    function CommentDisplayDirective($http, $compile, $templateCache) {

        return {
            restrict: 'E',
            controller: commentDisplay.CommentDisplayController,
            link: function (scope, element, attrs) {

                scope.$watch(attrs.profile, function (profile) {
                    if (profile != null) {

                        scope.$watch(attrs.role, function (role) {
                            if (role != null) {
                                loadTemplate('js/directives/comment-display/comment-display-template.html');
                            }
                        });

                        function loadTemplate(template) {
                            $http.get(template, { cache: $templateCache })
                                .success(function (templateContent) {
                                    element.replaceWith($compile(templateContent)(scope));
                                });
                        }
                    }
                });
            }
        };
    }

    commentDisplay.CommentDisplayDirective = CommentDisplayDirective;
})();
