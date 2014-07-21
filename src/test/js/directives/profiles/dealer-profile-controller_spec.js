(function() {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    describe('DealerProfileController', function() {
        var scope, DTOptionsBuilder, DTOptions, routeParams, dealerResource, dealerProfileResource, expectedDealerId, expectedProfileId, appRoleResource;
        var expectedDealerDeferred, expectedDealer, expectedProfileDeferred, expectedProfile, expectedRoleDeferred, expectedRole, expectedType;
        var ctrl;

        beforeEach(function() {
            angular.mock.module('sellIn.directives.profiles');

            angular.mock.module(function($provide) {
            	appRoleResource = jasmine.createSpyObj('appRoleResource',['get']);
            	$provide.decorator('appRoleResource',[function() {
            		return appRoleResource;
            	}]);
                dealerResource = jasmine.createSpyObj('dealerResource', ['get']);
                $provide.decorator('dealerResource', [function() {
                    return dealerResource;
                }]);
                dealerProfileResource = jasmine.createSpyObj('dealerProfileResource', ['get']);
                $provide.decorator('dealerProfileResource', [function() {
                    return dealerProfileResource;
                }]);
            });

            expectedProfileId = 1234;
            expectedDealerId = 111;
	        expectedType = 'T';
            routeParams = {dealerId: expectedDealerId, profileId: expectedProfileId, type: expectedType};

            DTOptionsBuilder = jasmine.createSpyObj('DTOptionsBuilder', ['newOptions']);

            DTOptions = jasmine.createSpyObj('DTOptions', ['withPaginationType', 'withDisplayLength', 'withBootstrap']);
            DTOptionsBuilder.newOptions.andReturn(DTOptions);
            DTOptions.withPaginationType.andReturn(DTOptions);
            DTOptions.withDisplayLength.andReturn(DTOptions);
        });

        beforeEach(inject(function($q, $rootScope, dealerResource, dealerProfileResource, appRoleResource) {
        	expectedRoleDeferred = $q.defer();
        	appRoleResource.get.andReturn(expectedRoleDeferred.promise);
        	expectedRole = {
        			name: 'U.T. Role',
        			roleId: 5
        	};
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);
            expectedDealer = {
                name: 'U.T. Dealer',
                dealerId: 999
            };

            expectedProfileDeferred = $q.defer();
            dealerProfileResource.get.andReturn(expectedProfileDeferred.promise);
            expectedProfile = {
                name: 'U.T. Profile',
                segments: [{
                    name: 'U.T. Segment Name',
                    orderSegments: [{
                        name: 'U.T. OrderSegment Name'
                    }]
                }]
            };

            scope = $rootScope.$new();
        }));

        describe('constructor', function() {
            it('initializes dealer on scope',
                inject(function($rootScope, dealerResource, dealerProfileResource, orderSegmentResourceMapper, appRoleResource) {
                ctrl = new dealerProfiles.DealerProfileDirectiveController(
                    scope,
                    DTOptionsBuilder,
                    routeParams,
                    dealerResource,
                    dealerProfileResource,
                    orderSegmentResourceMapper,
                    appRoleResource);

                var expectedDealer = {dealerId: expectedDealerId, type: expectedType};
                var expectedProfile = {profileId: expectedProfileId,dealerId: expectedDealerId};
                expect(dealerResource.get).toHaveBeenCalledWith(expectedDealer);
                expect(dealerProfileResource.get).toHaveBeenCalledWith(expectedProfile);

                expect(DTOptionsBuilder.newOptions).toHaveBeenCalled();
                expect(DTOptions.withPaginationType).toHaveBeenCalled();
                expect(DTOptions.withDisplayLength).toHaveBeenCalled();
                expect(DTOptions.withBootstrap).toHaveBeenCalled();
            }));
        });
    });
})();
