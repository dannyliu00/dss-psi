/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    describe('DealerProfileSummaryCtrl', function() {
        var scope, routeParams, dealerResource, dealerProfilesResource, expectedDealerId;
        var expectedDealerDeferred, expectedProfilesDeferred;
        var ctrl;

        beforeEach(function () {
            angular.mock.module('sellIn.pages.dealerProfileSummary');

            angular.mock.module(function ($provide) {
                dealerResource = jasmine.createSpyObj('dealerResource', ['get']);
                $provide.decorator('dealerResource', [function () {
                    return dealerResource;
                }]);
                dealerProfilesResource = jasmine.createSpyObj('dealerProfilesResource', ['query']);
                $provide.decorator('dealerProfilesResource', [function () {
                    return dealerProfilesResource;
                }]);
            });

            expectedDealerId = 111;
            routeParams = {dealerId: expectedDealerId};
        });

        beforeEach(inject(function ($q, $rootScope, $location, dealerResource, dealerProfilesResource) {
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);

            expectedProfilesDeferred = $q.defer();
            dealerProfilesResource.query.andReturn(expectedProfilesDeferred.promise);

            scope = {};
        }));

        describe('constructor', function () {
            it('initializes dealer on scope', inject(function (dealerResource, dealerProfilesResource) {
                ctrl = new dealerProfileSummary.DealerProfileSummaryCtrl(scope, routeParams, dealerResource, dealerProfilesResource);
                var expectedDealer = {dealerId: expectedDealerId};
                expect(dealerResource.get).toHaveBeenCalledWith(expectedDealer);
                expect(dealerProfilesResource.query).toHaveBeenCalledWith(expectedDealer);
            }));
        });
    });
})();