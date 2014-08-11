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
		          return resourceStrings[term];
		      }
		    };	
        });
})();