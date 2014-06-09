(function(){
	var emailEntry = sellInNamespace('sellIn.directives.emailentry');
	
	function EmailEntryDirective() {

        return {
            restrict: 'E',
            templateUrl: 'js/directives/inputs/email-entry-template.html'
        };
    }

    emailEntry.EmailEntryDirective = EmailEntryDirective;
})();