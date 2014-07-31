(function() {
	var emailRequired = sellInNamespace('sellIn.directives.emailrequired');

    angular.module('sellIn.directives.emailrequired', ['ngRoute', 'ui.bootstrap'])
        .controller('emailRequiredController', emailRequired.EmailRequiredController)
    ;
})();