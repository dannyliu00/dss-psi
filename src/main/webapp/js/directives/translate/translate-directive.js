(function() {
    var translate = sellInNamespace('sellIn.directives.translate');

    function TranslateDirective(translationPSI) {
    	
 	   return {
		   restrict: 'A',
		   link: function(scope, element, attrs) {
			   var text = element.text();
			   element.text(translationPSI.getString(text));
		   }
	   };
    }

    translate.TranslateDirective = TranslateDirective;
})();