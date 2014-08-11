(function () {
    var rsmButton = sellInNamespace('sellIn.directives.rsmbutton');

    function RsmButtonDirective($http, $compile, $templateCache) {

        return {
            restrict: 'E',
            controller: rsmButton.RsmButtonController,
            link: function (scope, element, attrs) {
                var basePath = 'js/directives/inputs/';
                var pathSuffix = '-button-template.html';

                scope.$watch(attrs.profile, function (profile) {
                    if (profile != null) {

                        var templateUrl = '';
                        if (scope.isEditable() === false) {
                            templateUrl = basePath + "rsm-readonly" + pathSuffix;
                            loadTemplate(templateUrl);
                        } else {
                            templateUrl = basePath + "rsm" + pathSuffix;
                            loadTemplate(templateUrl);
                        }
                    }
                });

                function loadTemplate(template) {
                    $http.get(template, { cache: $templateCache })
                        .success(function (templateContent) {
                            element.replaceWith($compile(templateContent)(scope));
                        });
                }
            }
        };
    }

    rsmButton.RsmButtonDirective = RsmButtonDirective;
})();