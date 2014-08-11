(function() {
	var commentDisplay = sellInNamespace('sellIn.directives.commentdisplay');

    describe('CommentDisplayController', function() {
        var ctrl, scope, expectedDealerComments, expectedDsmComments, expectedAdminComments, expectedShowComments, expectedRole, expectedProfile;

        beforeEach(function() {
            angular.mock.module('sellIn.directives.commentdisplay');
            
            expectedRole = {rsm: 'true', dsm: 'false'};
            expectedProfile = {orderSegments: [], profileId: 0};
            expectedOrderSegment = {description: '', dealerComments: '', dsmComments: '', adminComments: ''};
            expectedDealerComments = '';
            expectedDsmComments = '';
            expectedAdminComments = '';
            scope = {};

        });       
       
        beforeEach(inject(function($rootScope) {
        	scope.role = expectedRole;
        	scope.profile = expectedProfile;
        	scope.profile.orderSegments[0] = expectedOrderSegment;
        }));
        
        describe('constructor', function() {
            it('sets comments on scope based on user role', inject(function() {
            	
                ctrl = new commentDisplay.CommentDisplayController(scope, expectedDealerComments, expectedDsmComments, expectedAdminComments);
                
                expect(scope.profile.orderSegments[0].dealerComments).toEqual(expectedDealerComments);
                expect(scope.profile.orderSegments[0].dsmComments).toEqual(expectedDsmComments);
                expect(scope.profile.orderSegments[0].adminComments).toEqual(expectedAdminComments);
                
            }));
        });
    });
})();
