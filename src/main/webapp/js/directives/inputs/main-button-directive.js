(function () {
    var mainButton = sellInNamespace('sellIn.directives.mainbutton');

    function MainButtonDirective($http, $compile, $templateCache) {

        return {
            restrict: 'E',
            controller: mainButton.MainButtonDirectiveController,
            link: function (scope, element, attrs) {
                var basePath = 'js/directives/inputs/';
                var pathSuffix = '-button-template.html';

                scope.$watch(attrs.profile, function (profile) {
                    if (profile != null) {

                        var templateUrl = '';
                        if (scope.isEditable() === false) {
                            templateUrl = basePath + "readonly" + pathSuffix;
                            loadTemplate(templateUrl);
                        } else {
                            templateUrl = basePath + "main" + pathSuffix;
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

    mainButton.MainButtonDirective = MainButtonDirective;
})();