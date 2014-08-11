(function() {
    var translate = sellInNamespace('sellIn.directives.translate');

    function TranslateDirective(translationPSI) {
    	
 	   return {
		   restrict: 'A',
		   link: function(scope, element, attrs) {
			   var text = element.html();
			   element.html(translationPSI.getString(text));
		   }
	   };
    }

    translate.TranslateDirective = TranslateDirective;
})();