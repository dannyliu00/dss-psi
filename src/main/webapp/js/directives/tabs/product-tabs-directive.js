(function() {
    var productTabs = sellInNamespace('sellIn.directives.producttabs');

    function ProductTabsDirective($http, $compile, $templateCache) {
        return {
            restrict: 'E',
            controller: productTabs.ProductTabsController,
            link: function (scope, element, attrs) {
            	
    			scope.$watch(attrs.role, function(role) {
                	if(role != null) {		
        				templateUrl = 'js/directives/tabs/product-tabs-template.html';
        				loadTemplate(templateUrl);
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

    productTabs.ProductTabsDirective = ProductTabsDirective;
})();