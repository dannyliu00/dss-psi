(function () {
    var dsmButton = sellInNamespace('sellIn.directives.dsmbutton');

    function DsmButtonDirective($http, $compile, $templateCache) {

        return {
            restrict: 'E',
            controller: dsmButton.DsmButtonController,
            link: function (scope, element, attrs) {
                var basePath = 'js/directives/inputs/';
                var pathSuffix = '-button-template.html';

                scope.$watch(attrs.profile, function (profile) {
                    if (profile != null) {

                        var templateUrl = '';

                        if (scope.isEditable() === false) {
                            templateUrl = basePath + "nondealer-readonly" + pathSuffix;
                            loadTemplate(templateUrl);

                        } else {
                            templateUrl = basePath + "dsm" + pathSuffix;
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

    dsmButton.DsmButtonDirective = DsmButtonDirective;
})();