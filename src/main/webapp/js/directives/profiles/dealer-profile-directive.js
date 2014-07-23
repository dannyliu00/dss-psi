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
                    if(type != null) {
                    	if(scope.role.rsm === true || scope.role.admin === true){
                    		var templateUrl = basePath + "rsm-" + type + pathSuffix;
                            loadTemplate(templateUrl);
                    	} else if(scope.role.dsm === true) {
                    		var templateUrl = basePath + "dsm-" + type + pathSuffix;
                            loadTemplate(templateUrl);
                    	} else {
	                        var templateUrl = basePath + type + pathSuffix;
	                        loadTemplate(templateUrl);
                    	}
                    }
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