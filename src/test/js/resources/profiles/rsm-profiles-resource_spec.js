describe('RsmProfilesResource', function () {
    var httpBackend;

    beforeEach(function () {
        angular.mock.module('sellIn.resources.rsmProfiles');

        angular.mock.inject(function ($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('queryCurrent', function () {
        it('returns a promise with a list of dealer profiles of a specified rsm and of a specified type', inject(function (rsmProfilesResource, rsmCurrentProfilesUrl) {
            var type = 'vehicle';
            var r = 12345;
            var rsm = {type: type, r: r};
            var expectedRequest = rsmCurrentProfilesUrl.replace(':type', type);
            expectedRequest += '?r=' + r;
            var expectedList = [
                {name: 'U.T. Victory Profile For A Dealer'}
            ];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = rsmProfilesResource.queryCurrent(rsm);

            promise.then(function (actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });

    describe('queryHistory', function () {
        it('returns a promise with a list of dealer profiles of a specified rsm and of a specified type', inject(function (rsmProfilesResource, rsmHistoryProfilesUrl) {
            var type = 'vehicle';
            var r = 12345;
            var rsm = {type: type, r: r};
            var expectedRequest = rsmHistoryProfilesUrl.replace(':type', type);
            expectedRequest += '?r=' + r;
            var expectedList = [
                {name: 'U.T. Victory Profile For A Dealer'}
            ];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = rsmProfilesResource.queryHistory(rsm);

            promise.then(function (actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });
});
