(function () {
	var dsmButton = sellInNamespace('sellIn.directives.dsmbutton');

    describe('DsmButtonController', function () {
        var scope, expectedOrderSegments, expectedState, expectedType, lastTab, ctrl, compliant, expectedCaption, divClass;

        beforeEach(function () {
            angular.mock.module('sellIn.directives.dsmbutton');
            
            divClass = '.compliant';

            angular.mock.module(function ($provide) {
                lastTab = jasmine.createSpyObj('lastTab', ['changeProductTab', 'changeProfilesTab']);
                $provide.decorator('lastTab', [function () {
                    return lastTab;
                }]);
            });
        });

        beforeEach(inject(function ($rootScope) {
        	
        	scope = $rootScope.$new;
        	
        	expectedOrderSegments = [{actual: 0, dsmQty: 0}];
        	
        	scope.orderSegments = expectedOrderSegments;
        	
        	compliant = angular.element(divClass).hasClass('.nonCompliant');
        	
        	expectedCaption = 'Approve as Requested';
            
            expectedState = false;
            
            expectedType = 'T';
            
        }));

        describe('constructor', function () {
            it('sets caption based on status of profile',
                inject(function ($rootScope, lastTab) {                	
                	
                    ctrl = new dsmButton.DsmButtonController(
                        scope,
                        compliant,
                        expectedType,
                        expectedCaption,
                        expectedState,
                        lastTab);
                    
                    $rootScope.$digest();
                	
                    expect(compliant).toEqual(expectedState);
                    expect(scope.dsmButtonCaptionFill()).toEqual(expectedCaption);

                }));
        });
    });
})();
