(function() {
	var dealerInfo = sellInNamespace('sellIn.directives.dealerInfo');

    describe('DealerInfoController', function() {
        var ctrl, scope, expectedCaption, routeParams, expectedType;

        beforeEach(function() {
            angular.mock.module('sellIn.directives.dealerInfo');
            
            expectedType = '5';
            expectedCaption = 'DRM';

        });       
       
        beforeEach(inject(function($rootScope) {
        	scope = $rootScope.$new;
        }));
       
        
        
        describe('constructor', function() {
            it('selects caption based on type', inject(function() {
            	
                ctrl = new dealerInfo.DealerInfoController(scope, expectedCaption, routeParams, expectedType);
                
                var caption = '';
                
                expect(scope.roleCaption(expectedType)).toEqual(expectedCaption);
                
            }));
        });
    });
})();