(function () {
	angular.module('sellIn.services.translation',['sellIn.resources.translate'])
        .factory('translationPSI', function (translationResource) {
		
        	var resourceStrings=null;
		
        	var promise = translationResource.get().then(function(data) {
        		resourceStrings=data;
		   });

		return {
		      promise:promise,
		      setData: function (data) {
		    	  resourceStrings = data;
		      },
		      getString: function (term) {
		    	  term=term.trim();
		          var value = resourceStrings[term];
		          
		          if(!value) {
		        	  // This string does not exist. We'll ask for it to be added and return the same string.
		        	  translationResource.addString(term).then(function(data) {
		          		
		        	  });
		        	  // Return this to the client. 
		        	  value = term;
		          }
		          
		          return value;
		      }
		    };	
        });
})();