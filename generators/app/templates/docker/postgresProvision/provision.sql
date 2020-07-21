CREATE USER <%=schemaSimpleServiceName%>_dev WITH PASSWORD 'corelogic1';
CREATE USER <%=schemaSimpleServiceName%>_test WITH PASSWORD 'corelogic1';

CREATE SCHEMA IF NOT EXISTS <%=schemaSimpleServiceName%>_service_dev AUTHORIZATION <%=schemaSimpleServiceName%>_dev;
CREATE SCHEMA IF NOT EXISTS <%=schemaSimpleServiceName%>_service_test AUTHORIZATION <%=schemaSimpleServiceName%>_test;

ALTER ROLE <%=schemaSimpleServiceName%>_dev SET search_path to <%=schemaSimpleServiceName%>_service_dev;
ALTER ROLE <%=schemaSimpleServiceName%>_test SET search_path to <%=schemaSimpleServiceName%>_service_test;

DROP SCHEMA public cascade;