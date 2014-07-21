/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    describe('DealerProfileSummaryCtrl', function() {
        var scope, DTOptionsBuilder, DTOptions, routeParams, location, dealerResource, dealerProfilesResource, expectedDealerId, expectedUrl, expectedType;
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

            location = jasmine.createSpyObj('location', ['path']);

            expectedDealerId = 111;
	        expectedType = 'T';
            routeParams = {dealerId: expectedDealerId, type: expectedType};
            expectedUrl = '/segment1/:dealerId/segment2/:profileId/segment3/:type';

            DTOptionsBuilder = jasmine.createSpyObj('DTOptionsBuilder', ['newOptions']);

            DTOptions = jasmine.createSpyObj('DTOptions', ['withPaginationType', 'withDisplayLength', 'withBootstrap']);
            DTOptionsBuilder.newOptions.andReturn(DTOptions);
            DTOptions.withPaginationType.andReturn(DTOptions);
            DTOptions.withDisplayLength.andReturn(DTOptions);
        });

        beforeEach(inject(function ($q, $rootScope, dealerResource, dealerProfilesResource) {
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);

            expectedProfilesDeferred = $q.defer();
            dealerProfilesResource.query.andReturn(expectedProfilesDeferred.promise);

            scope = $rootScope.$new();
        }));

        describe('constructor', function () {
            it('initializes dealer on scope', inject(function ($rootScope, dealerResource, dealerProfilesResource) {
                ctrl = new dealerProfileSummary.DealerProfileSummaryCtrl(
                    scope,
                    routeParams,
                    location,
                    dealerResource,
                    dealerProfilesResource,
                    expectedUrl,
                    DTOptionsBuilder);

	            var expectedDealerAndType = {dealerId: expectedDealerId, type: expectedType};
	            var expectedDealer = {dealerId: expectedDealerId, type: expectedType};
                expect(dealerResource.get).toHaveBeenCalledWith(expectedDealerAndType);
                expect(dealerProfilesResource.query).toHaveBeenCalledWith(expectedDealer);

//                expect(DTOptionsBuilder.newOptions).toHaveBeenCalled();
//                expect(DTOptions.withPaginationType).toHaveBeenCalled();
//                expect(DTOptions.withDisplayLength).toHaveBeenCalled();
//                expect(DTOptions.withBootstrap).toHaveBeenCalled();
            }));
        });

    });
})();
