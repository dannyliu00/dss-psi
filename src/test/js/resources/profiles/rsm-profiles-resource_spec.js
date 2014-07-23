describe('DsmProfilesResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.rsmProfiles');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('queryCurrent', function() {
        it('returns a promise with a list of dealer profiles of a specified dsm and of a specified type', inject(function(rsmProfilesResource, rsmCurrentProfilesUrl) {
            var rsmId = 123456;
            var type = 'vehicle';
            var rsm = {rsmId: rsmId, type: type};
            var expectedRequest = rsmCurrentProfilesUrl.replace(':rsmId', rsmId).replace(':type', type);
            expectedRequest += '?ran=' + new Date().getTime();
            var expectedList = [{name: 'U.T. Victory Profile For A Dealer'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = rsmProfilesResource.queryCurrent(rsm);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });

    describe('queryHistory', function() {
        it('returns a promise with a list of dealer profiles of a specified dsm and of a specified type', inject(function(rsmProfilesResource, rsmHistoryProfilesUrl) {
            var rsmId = 123456;
            var type = 'vehicle';
            var rsm = {rsmId: rsmId, type: type};
            var expectedRequest = rsmHistoryProfilesUrl.replace(':rsmId', rsmId).replace(':type', type);
            expectedRequest += '?ran=' + new Date().getTime();
            var expectedList = [{name: 'U.T. Victory Profile For A Dealer'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = rsmProfilesResource.queryHistory(rsm);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });
});
