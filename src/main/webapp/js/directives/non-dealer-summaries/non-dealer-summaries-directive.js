(function () {
    var nonDealerSummaries = sellInNamespace('sellIn.directives.nondealersummaries');

    function NonDealerSummariesDirective($http, $compile, $templateCache, DTOptionsBuilder) {

        return {
            restrict: 'E',
            controller: nonDealerSummaries.NonDealerSummariesController,
            link: function (scope, element, attrs) {

                scope.$watch(attrs.profiles, function (profiles) {
                    if (profiles != null) {

                        scope.$watch(attrs.role, function (role) {
                            if (role != null) {
                                var templateUrl = '';
                                var basePath = 'js/directives/non-dealer-summaries/';
                                var pathSuffix = '-summary-datatables-template.html';
                                
                                //DT Options set in directive due to controller instantiating prior to directive.  Creates and 
                                //prevents load.
                                
                                if (role.rsm === true || role.admin === true) {
                                    templateUrl = basePath + "rsm" + pathSuffix;
                                    loadTemplate(templateUrl);
                                    scope.dtOptions = DTOptionsBuilder.newOptions()
	                		            .withPaginationType('full_numbers')
	                		            .withDisplayLength(25)
	                		            .withBootstrap()
	                		            .withOption('order',[[1,"asc"],[3,"asc"]]);
                                } else if (role.dsm === true) {
                                    templateUrl = basePath + "dsm" + pathSuffix;
                                    loadTemplate(templateUrl);
                                    scope.dtOptions = DTOptionsBuilder.newOptions()
	                		            .withPaginationType('full_numbers')
	                		            .withDisplayLength(25)
	                		            .withBootstrap()
	                		            .withOption('order',[2,"asc"]);
                                }
                            }
                        });
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

    nonDealerSummaries.NonDealerSummariesDirective = NonDealerSummariesDirective;
})();