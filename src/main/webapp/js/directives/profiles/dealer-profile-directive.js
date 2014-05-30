(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function ProfileDetailsDirective($http, $templateCache, $compile) {

        return {
            restrict: 'E',
            controller: dealerProfiles.DealerProfileDirectiveController,
            link: function (scope, element, attrs) {
                var basePath = 'js/directives/profiles/';
                var pathSuffix = '-template.html';

                scope.$watch(attrs.type, function(type) {
                    var templateUrl = basePath + type + pathSuffix;
                    loadTemplate(templateUrl);
                });

                function loadTemplate(template) {
                    $http.get(template, { cache: $templateCache })
                        .success(function(templateContent) {
                            element.replaceWith($compile(templateContent)(scope));
                        });
                }
            }
        };
    }

    dealerProfiles.ProfileDetailsDirective = ProfileDetailsDirective;
})();