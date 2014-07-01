(function() {
    var nonDealerSummaries = sellInNamespace('sellIn.directives.nondealersummaries');

    function NonDealerSummariesDirective($http, $compile, $templateCache) {

        return {
            restrict: 'E',
            controller: nonDealerSummaries.NonDealerSummariesController,
            link: function (scope, element, attrs) {
                var basePath = 'js/directives/non-dealer-summaries/';
                var pathSuffix = '-summary-datatables-template.html';
                
                scope.$watch(attrs.role, function(role) {
                	if(role != null) {
                		if(role.admin === true) {
                			var templateUrl = basePath + "admin" + pathSuffix;
                			loadTemplate(templateUrl);
                		} else if (role.rsm === true) {
                			var templateUrl = basePath + "rsm" + pathSuffix;
                			loadTemplate(templateUrl);
                		} else if(role.dsm === true) {
                			var templateUrl = basePath + "dsm" + pathSuffix;
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

    nonDealerSummaries.NonDealerSummariesDirective = NonDealerSummariesDirective;
})();