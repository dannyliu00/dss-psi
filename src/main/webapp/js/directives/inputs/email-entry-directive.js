(function(){
	var emailEntry = sellInNamespace('sellIn.directives.emailentry');
	
	function EmailEntryDirective($http, $compile, $templateCache) {

        return {
            restrict: 'E',
            link: function (scope, element, attrs) {
                
                scope.$watch(attrs.profile, function(profile) {
                	if(profile != null) {
                		
		                if(scope.isEditable() === true) {
		                	templateUrl = 'js/directives/inputs/email-entry-template.html';
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

    emailEntry.EmailEntryDirective = EmailEntryDirective;
})();