## 一、Mysql 8.0
函数名     | 描述
-------- | -----
ABS()  | Return the absolute value
ACOS() |	Return the arc cosine
ADDDATE() |	Add time values (intervals) to a date value
ADDTIME() |	Add time
AES_DECRYPT() |	Decrypt using AES
AES_ENCRYPT() |	Encrypt using AES
AND, && |	Logical AND
ANY_VALUE() |	Suppress ONLY_FULL_GROUP_BY value rejection
ASCII() |	Return numeric value of left-most character
ASIN() |	Return the arc sine
= |	Assign a value (as part of a SET statement, or as part of the SET clause in an UPDATE statement)
:= |	Assign a value
ASYMMETRIC_DECRYPT() |	Decrypt ciphertext using private or public key
ASYMMETRIC_DERIVE() |	Derive symmetric key from asymmetric keys
ASYMMETRIC_ENCRYPT() |	Encrypt cleartext using private or public key
ASYMMETRIC_SIGN() |	Generate signature from digest
ASYMMETRIC_VERIFY() |	Verify that signature matches digest
ATAN() |	Return the arc tangent
ATAN2(), ATAN() |	Return the arc tangent of the two arguments
AVG() |	Return the average value of the argument
BENCHMARK() |	Repeatedly execute an expression
BETWEEN ... AND ... |	Whether a value is within a range of values
BIN() |	Return a string containing binary representation of a number
BIN_TO_UUID() |	Convert binary UUID to string
BINARY |	Cast a string to a binary string
BIT_AND() |	Return bitwise AND
BIT_COUNT() |	Return the number of bits that are set
BIT_LENGTH() |	Return length of argument in bits
BIT_OR() |	Return bitwise OR
BIT_XOR() |	Return bitwise XOR
& |	Bitwise AND
~ |	Bitwise inversion
| |	Bitwise OR
^ |	Bitwise XOR
CAN_ACCESS_COLUMN() |	Internal use only
CAN_ACCESS_DATABASE() |	Internal use only
CAN_ACCESS_TABLE() |	Internal use only
CAN_ACCESS_VIEW() |	Internal use only
CASE |	Case operator
CAST() |	Cast a value as a certain type
CEIL() |	Return the smallest integer value not less than the argument
CEILING() |	Return the smallest integer value not less than the argument
CHAR() |	Return the character for each integer passed
CHAR_LENGTH() |	Return number of characters in argument
CHARACTER_LENGTH() |	Synonym for CHAR_LENGTH()
CHARSET() |	Return the character set of the argument
COALESCE() |	Return the first non-NULL argument
COERCIBILITY() |	Return the collation coercibility value of the string argument
COLLATION() |	Return the collation of the string argument
COMPRESS() |	Return result as a binary string
CONCAT() |	Return concatenated string
CONCAT_WS() |	Return concatenate with separator
CONNECTION_ID() |	Return the connection ID (thread ID) for the connection
CONV() |	Convert numbers between different number bases
CONVERT() |	Cast a value as a certain type
CONVERT_TZ() |	Convert from one time zone to another
COS() |	Return the cosine
COT() |	Return the cotangent
COUNT() |	Return a count of the number of rows returned
COUNT(DISTINCT) |	Return the count of a number of different values
CRC32() |	Compute a cyclic redundancy check value
CREATE_ASYMMETRIC_PRIV_KEY() |	Create private key
CREATE_ASYMMETRIC_PUB_KEY() |	Create public key
CREATE_DH_PARAMETERS() |	Generate shared DH secret
CREATE_DIGEST() |	Generate digest from string
CUME_DIST() |	Cumulative distribution value
CURDATE() |	Return the current date
CURRENT_DATE() |, CURRENT_DATE	Synonyms for CURDATE()
CURRENT_ROLE() |	Return the current active roles
CURRENT_TIME(), CURRENT_TIME |	Synonyms for CURTIME()
CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP |	Synonyms for NOW()
CURRENT_USER(), CURRENT_USER |	The authenticated user name and host name
CURTIME() |	Return the current time
DATABASE() |	Return the default (current) database name
DATE() |	Extract the date part of a date or datetime expression
DATE_ADD() |	Add time values (intervals) to a date value
DATE_FORMAT() |	Format date as specified
DATE_SUB() |	Subtract a time value (interval) from a date
DATEDIFF() |	Subtract two dates
DAY() |	Synonym for DAYOFMONTH()
DAYNAME() |	Return the name of the weekday
DAYOFMONTH() |	Return the day of the month (0-31)
DAYOFWEEK() |	Return the weekday index of the argument
DAYOFYEAR() |	Return the day of the year (1-366)
DECODE() |	Decode a string encrypted using ENCODE()
DEFAULT() |	Return the default value for a table column
DEGREES() |	Convert radians to degrees
DENSE_RANK() |	Rank of current row within its partition, without gaps
DES_DECRYPT() |	Decrypt a string
DES_ENCRYPT() |	Encrypt a string
DIV |	Integer division
| |	Division operator
ELT() |	Return string at index number
ENCODE() |	Encode a string
ENCRYPT() |	Encrypt a string
= |	Equal operator
<=> |	NULL-safe equal to operator
EXP() |	Raise to the power of
EXPORT_SET() |	Return a string such that for every bit set in the value bits, you get an on string and for every unset bit, you get an off string
EXTRACT() |	Extract part of a date
ExtractValue() |	Extract a value from an XML string using XPath notation
FIELD() |	Index (position) of first argument in subsequent arguments
FIND_IN_SET() |	Index (position) of first argument within second argument
FIRST_VALUE() |	Value of argument from first row of window frame
FLOOR() |	Return the largest integer value not greater than the argument
FORMAT() |	Return a number formatted to specified number of decimal places
FORMAT_BYTES() |	Convert byte count to value with units
FORMAT_PICO_TIME() |	Convert time in picoseconds to value with units
FOUND_ROWS() |	For a SELECT with a LIMIT clause, the number of rows that would be returned were there no LIMIT clause
FROM_BASE64() |	Decode base64 encoded string and return result
FROM_DAYS() |	Convert a day number to a date
FROM_UNIXTIME() |	Format Unix timestamp as a date
GeomCollection() |	Construct geometry collection from geometries
GeometryCollection() |	Construct geometry collection from geometries
GET_DD_COLUMN_PRIVILEGES() |	Internal use only
GET_DD_CREATE_OPTIONS() |	Internal use only
GET_DD_INDEX_SUB_PART_LENGTH() |	Internal use only
GET_FORMAT() |	Return a date format string
GET_LOCK() |	Get a named lock
> |	Greater than operator
>= |	Greater than or equal operator
GREATEST() |	Return the largest argument
GROUP_CONCAT() |	Return a concatenated string
GROUPING() |	Distinguish super-aggregate ROLLUP rows from regular rows
GTID_SUBSET() |	Return true if all GTIDs in subset are also in set; otherwise false.
GTID_SUBTRACT() |	Return all GTIDs in set that are not in subset.
HEX() |	Hexadecimal representation of decimal or string value
HOUR() |	Extract the hour
ICU_VERSION() |	ICU library version
IF() |	If|else construct
IFNULL() |	Null if|else construct
IN() |	Whether a value is within a set of values
INET_ATON() |	Return the numeric value of an IP address
INET_NTOA() |	Return the IP address from a numeric value
INET6_ATON() |	Return the numeric value of an IPv6 address
INET6_NTOA() |	Return the IPv6 address from a numeric value
INSERT() |	Insert substring at specified position up to specified number of characters
INSTR() |	Return the index of the first occurrence of substring
INTERNAL_AUTO_INCREMENT() |	Internal use only
INTERNAL_AVG_ROW_LENGTH() |	Internal use only
INTERNAL_CHECK_TIME() |	Internal use only
INTERNAL_CHECKSUM() |	Internal use only
INTERNAL_DATA_FREE() |	Internal use only
INTERNAL_DATA_LENGTH() |	Internal use only
INTERNAL_DD_CHAR_LENGTH() |	Internal use only
INTERNAL_GET_COMMENT_OR_ERROR() |	Internal use only
INTERNAL_GET_ENABLED_ROLE_JSON() |	Internal use only
INTERNAL_GET_HOSTNAME() |	Internal use only
INTERNAL_GET_USERNAME() |	Internal use only
INTERNAL_GET_VIEW_WARNING_OR_ERROR() |	Internal use only
INTERNAL_INDEX_COLUMN_CARDINALITY() |	Internal use only
INTERNAL_INDEX_LENGTH() |	Internal use only
INTERNAL_IS_ENABLED_ROLE() |	Internal use only
INTERNAL_IS_MANDATORY_ROLE() |	Internal use only
INTERNAL_KEYS_DISABLED() |	Internal use only
INTERNAL_MAX_DATA_LENGTH() |	Internal use only
INTERNAL_TABLE_ROWS() |	Internal use only
INTERNAL_UPDATE_TIME() |	Internal use only
INTERVAL() |	Return the index of the argument that is less than the first argument
IS |	Test a value against a boolean
IS_FREE_LOCK() |	Whether the named lock is free
IS_IPV4() |	Whether argument is an IPv4 address
IS_IPV4_COMPAT() |	Whether argument is an IPv4-compatible address
IS_IPV4_MAPPED() |	Whether argument is an IPv4-mapped address
IS_IPV6() |	Whether argument is an IPv6 address
IS NOT |	Test a value against a boolean
IS NOT | NULL	NOT NULL value test
IS NULL |	NULL value test
IS_USED_LOCK() |	Whether the named lock is in use; return connection identifier if true
IS_UUID() |	Whether argument is a valid UUID
ISNULL() |	Test whether the argument is NULL
JSON_ARRAY() |	Create JSON array
JSON_ARRAY_APPEND() |	Append data to JSON document
JSON_ARRAY_INSERT() |	Insert into JSON array
JSON_ARRAYAGG() |	Return result set as a single JSON array
-> |	Return value from JSON column after evaluating path; equivalent to JSON_EXTRACT().
JSON_CONTAINS() |	Whether JSON document contains specific object at path
JSON_CONTAINS_PATH() |	Whether JSON document contains any data at path
JSON_DEPTH() |	Maximum depth of JSON document
JSON_EXTRACT() |	Return data from JSON document
->> |	Return value from JSON column after evaluating path and unquoting the result; equivalent to JSON_UNQUOTE(JSON_EXTRACT()).
JSON_INSERT() |	Insert data into JSON document
JSON_KEYS() |	Array of keys from JSON document
JSON_LENGTH() |	Number of elements in JSON document
JSON_MERGE() | (deprecated 8.0.3)	Merge JSON documents, preserving duplicate keys. Deprecated synonym for JSON_MERGE_PRESERVE()
JSON_MERGE_PATCH() |	Merge JSON documents, replacing values of duplicate keys
JSON_MERGE_PRESERVE() |	Merge JSON documents, preserving duplicate keys
JSON_OBJECT() |	Create JSON object
JSON_OBJECTAGG() |	Return result set as a single JSON object
JSON_OVERLAPS() |	Compares two JSON documents, returns TRUE (1) if these have any key-value pairs or array elements in common, otherwise FALSE (0)
JSON_PRETTY() |	Print a JSON document in human-readable format
JSON_QUOTE() |	Quote JSON document
JSON_REMOVE() |	Remove data from JSON document
JSON_REPLACE() |	Replace values in JSON document
JSON_SCHEMA_VALID() |	Validate JSON document against JSON schema; returns TRUE|1 if document validates against schema, or FALSE|0 if it does not
JSON_SCHEMA_VALIDATION_REPORT() |	Validate JSON document against JSON schema; returns report in JSON format on outcome on validation including success or failure and reasons for failure
JSON_SEARCH() |	Path to value within JSON document
JSON_SET() |	Insert data into JSON document
JSON_STORAGE_FREE() |	Freed space within binary representation of JSON column value following partial update
JSON_STORAGE_SIZE() |	Space used for storage of binary representation of a JSON document
JSON_TABLE() |	Return data from a JSON expression as a relational table
JSON_TYPE() |	Type of JSON value
JSON_UNQUOTE() |	Unquote JSON value
JSON_VALID() |	Whether JSON value is valid
LAG() |	Value of argument from row lagging current row within partition
LAST_DAY |	Return the last day of the month for the argument
LAST_INSERT_ID() |	Value of the AUTOINCREMENT column for the last INSERT
LAST_VALUE() |	Value of argument from last row of window frame
LCASE() |	Synonym for LOWER()
LEAD() |	Value of argument from row leading current row within partition
LEAST() |	Return the smallest argument
LEFT() |	Return the leftmost number of characters as specified
<< |	Left shift
LENGTH() |	Return the length of a string in bytes
< |	Less than operator
<= |	Less than or equal operator
LIKE |	Simple pattern matching
LineString() |	Construct LineString from Point values
LN() |	Return the natural logarithm of the argument
LOAD_FILE() |	Load the named file
LOCALTIME(), LOCALTIME |	Synonym for NOW()
LOCALTIMESTAMP, LOCALTIMESTAMP() |	Synonym for NOW()
LOCATE() |	Return the position of the first occurrence of substring
LOG() |	Return the natural logarithm of the first argument
LOG10() |	Return the base-10 logarithm of the argument
LOG2() |	Return the base-2 logarithm of the argument
LOWER() |	Return the argument in lowercase
LPAD() |	Return the string argument, left-padded with the specified string
LTRIM() |	Remove leading spaces
MAKE_SET() |	Return a set of comma-separated strings that have the corresponding bit in bits set
MAKEDATE() |	Create a date from the year and day of year
MAKETIME() |	Create time from hour, minute, second
MASTER_POS_WAIT() |	Block until the slave has read and applied all updates up to the specified position
MATCH |	Perform full-text search
MAX() |	Return the maximum value
MBRContains() |	Whether MBR of one geometry contains MBR of another
MBRCoveredBy() |	Whether one MBR is covered by another
MBRCovers() |	Whether one MBR covers another
MBRDisjoint() |	Whether MBRs of two geometries are disjoint
MBREquals() |	Whether MBRs of two geometries are equal
MBRIntersects() |	Whether MBRs of two geometries intersect
MBROverlaps() |	Whether MBRs of two geometries overlap
MBRTouches() |	Whether MBRs of two geometries touch
MBRWithin() |	Whether MBR of one geometry is within MBR of another
MD5() |	Calculate MD5 checksum
MEMBER OF( |)	Returns true (1) if first operand matches any element of JSON array passed as second operand, otherwise returns false (0)
MICROSECOND() |	Return the microseconds from argument
MID() |	Return a substring starting from the specified position
MIN() |	Return the minimum value
- |	Minus operator
MINUTE() |	Return the minute from the argument
MOD() |	Return the remainder
%, MOD |	Modulo operator
MONTH() |	Return the month from the date passed
MONTHNAME() |	Return the name of the month
MultiLineString() |	Contruct MultiLineString from LineString values
MultiPoint() |	Construct MultiPoint from Point values
MultiPolygon() |	Construct MultiPolygon from Polygon values
NAME_CONST() |	Cause the column to have the given name
NOT, ! |	Negates value
NOT BETWEEN ... AND ... |	Whether a value is not within a range of values
!=, <> |	Not equal operator
NOT IN() |	Whether a value is not within a set of values
NOT LIKE |	Negation of simple pattern matching
NOT REGEXP |	Negation of REGEXP
NOW() |	Return the current date and time
NTH_VALUE() |	Value of argument from N-th row of window frame
NTILE() |	Bucket number of current row within its partition.
NULLIF() |	Return NULL if expr1 = expr2
OCT() |	Return a string containing octal representation of a number
OCTET_LENGTH() |	Synonym for LENGTH()
OR, || |	Logical OR
ORD() |	Return character code for leftmost character of the argument
PASSWORD() |	Calculate and return a password string
PERCENT_RANK() |	Percentage rank value
PERIOD_ADD() |	Add a period to a year-month
PERIOD_DIFF() |	Return the number of months between periods
PI() |	Return the value of pi
+ |	Addition operator
Point() |	Construct Point from coordinates
Polygon() |	Construct Polygon from LineString arguments
POSITION() |	Synonym for LOCATE()
POW() |	Return the argument raised to the specified power
POWER() |	Return the argument raised to the specified power
PS_CURRENT_THREAD_ID() |	Performance Schema thread ID for current thread
PS_THREAD_ID() |	Performance Schema thread ID for given thread
QUARTER() |	Return the quarter from a date argument
QUOTE() |	Escape the argument for use in an SQL statement
RADIANS() |	Return argument converted to radians
RAND() |	Return a random floating-point value
RANDOM_BYTES() |	Return a random byte vector
RANK() |	Rank of current row within its partition, with gaps
REGEXP |	Whether string matches regular expression
REGEXP_INSTR() |	Starting index of substring matching regular expression
REGEXP_LIKE() |	Whether string matches regular expression
REGEXP_REPLACE() |	Replace substrings matching regular expression
REGEXP_SUBSTR() |	Return substring matching regular expression
RELEASE_ALL_LOCKS() |	Release all current named locks
RELEASE_LOCK() |	Release the named lock
REPEAT() |	Repeat a string the specified number of times
REPLACE() |	Replace occurrences of a specified string
REVERSE() |	Reverse the characters in a string
RIGHT() |	Return the specified rightmost number of characters
>> |	Right shift
RLIKE |	Whether string matches regular expression
ROLES_GRAPHML() |	Return a GraphML document representing memory role subgraphs
ROUND() |	Round the argument
ROW_COUNT() |	The number of rows updated
ROW_NUMBER() |	Number of current row within its partition
RPAD() |	Append string the specified number of times
RTRIM() |	Remove trailing spaces
SCHEMA() |	Synonym for DATABASE()
SEC_TO_TIME() |	Converts seconds to 'hh:mm:ss' format
SECOND() |	Return the second (0-59)
SESSION_USER() |	Synonym for USER()
SHA1(), SHA() |	Calculate an SHA-1 160-bit checksum
SHA2() |	Calculate an SHA-2 checksum
SIGN() |	Return the sign of the argument
SIN() |	Return the sine of the argument
SLEEP() |	Sleep for a number of seconds
SOUNDEX() |	Return a soundex string
SOUNDS LIKE |	Compare sounds
SPACE() |	Return a string of the specified number of spaces
SQRT() |	Return the square root of the argument
ST_Area() |	Return Polygon or MultiPolygon area
ST_AsBinary() |, ST_AsWKB()	Convert from internal geometry format to WKB
ST_AsGeoJSON() |	Generate GeoJSON object from geometry
ST_AsText(), ST_AsWKT() |	Convert from internal geometry format to WKT
ST_Buffer() |	Return geometry of points within given distance from geometry
ST_Buffer_Strategy() |	Produce strategy option for ST_Buffer()
ST_Centroid() |	Return centroid as a point
ST_Contains() |	Whether one geometry contains another
ST_ConvexHull() |	Return convex hull of geometry
ST_Crosses() |	Whether one geometry crosses another
ST_Difference() |	Return point set difference of two geometries
ST_Dimension() |	Dimension of geometry
ST_Disjoint() |	Whether one geometry is disjoint from another
ST_Distance() |	The distance of one geometry from another
ST_Distance_Sphere() |	Minimum distance on earth between two geometries
ST_EndPoint() |	End Point of LineString
ST_Envelope() |	Return MBR of geometry
ST_Equals() |	Whether one geometry is equal to another
ST_ExteriorRing() |	Return exterior ring of Polygon
ST_GeoHash() |	Produce a geohash value
ST_GeomCollFromText() , ST_GeometryCollectionFromText(), ST_GeomCollFromTxt() |	Return geometry collection from WKT
ST_GeomCollFromWKB(), ST_GeometryCollectionFromWKB() |	Return geometry collection from WKB
ST_GeometryN() |	Return N-th geometry from geometry collection
ST_GeometryType() |	Return name of geometry type
ST_GeomFromGeoJSON() |	Generate geometry from GeoJSON object
ST_GeomFromText(), ST_GeometryFromText() |	Return geometry from WKT
ST_GeomFromWKB(), ST_GeometryFromWKB() |	Return geometry from WKB
ST_InteriorRingN() |	Return N-th interior ring of Polygon
ST_Intersection() |	Return point set intersection of two geometries
ST_Intersects() |	Whether one geometry intersects another
ST_IsClosed() |	Whether a geometry is closed and simple
ST_IsEmpty() |	Placeholder function
ST_IsSimple() |	Whether a geometry is simple
ST_IsValid() |	Whether a geometry is valid
ST_LatFromGeoHash() |	Return latitude from geohash value
ST_Latitude() |	Return latitude of Point
ST_Length() |	Return length of LineString
ST_LineFromText(), ST_LineStringFromText() |	Construct LineString from WKT
ST_LineFromWKB(), ST_LineStringFromWKB() |	Construct LineString from WKB
ST_LongFromGeoHash() |	Return longitude from geohash value
ST_Longitude() |	Return longitude of Point
ST_MakeEnvelope() |	Rectangle around two points
ST_MLineFromText(), ST_MultiLineStringFromText() |	Construct MultiLineString from WKT
ST_MLineFromWKB(), ST_MultiLineStringFromWKB() |	Construct MultiLineString from WKB
ST_MPointFromText(), ST_MultiPointFromText() |	Construct MultiPoint from WKT
ST_MPointFromWKB(), ST_MultiPointFromWKB() |	Construct MultiPoint from WKB
ST_MPolyFromText(), ST_MultiPolygonFromText() |	Construct MultiPolygon from WKT
ST_MPolyFromWKB(), ST_MultiPolygonFromWKB() |	Construct MultiPolygon from WKB
ST_NumGeometries() |	Return number of geometries in geometry collection
ST_NumInteriorRing(), ST_NumInteriorRings() |	Return number of interior rings in Polygon
ST_NumPoints() |	Return number of points in LineString
ST_Overlaps() |	Whether one geometry overlaps another
ST_PointFromGeoHash() |	Convert geohash value to POINT value
ST_PointFromText() |	Construct Point from WKT
ST_PointFromWKB() |	Construct Point from WKB
ST_PointN() |	Return N-th point from LineString
ST_PolyFromText(), ST_PolygonFromText() |	Construct Polygon from WKT
ST_PolyFromWKB(), ST_PolygonFromWKB() |	Construct Polygon from WKB
ST_Simplify() |	Return simplified geometry
ST_SRID() |	Return spatial reference system ID for geometry
ST_StartPoint() |	Start Point of LineString
ST_SwapXY() |	Return argument with X|Y coordinates swapped
ST_SymDifference() |	Return point set symmetric difference of two geometries
ST_Touches() |	Whether one geometry touches another
ST_Transform() |	Transform coordinates of geometry
ST_Union() |	Return point set union of two geometries
ST_Validate() |	Return validated geometry
ST_Within() |	Whether one geometry is within another
ST_X() |	Return X coordinate of Point
ST_Y() |	Return Y coordinate of Point
STATEMENT_DIGEST() |	Compute statement digest hash value
STATEMENT_DIGEST_TEXT() |	Compute normalized statement digest
STD() |	Return the population standard deviation
STDDEV() |	Return the population standard deviation
STDDEV_POP() |	Return the population standard deviation
STDDEV_SAMP() |	Return the sample standard deviation
STR_TO_DATE() |	Convert a string to a date
STRCMP() |	Compare two strings
SUBDATE() |	Synonym for DATE_SUB() when invoked with three arguments
SUBSTR() |	Return the substring as specified
SUBSTRING() |	Return the substring as specified
SUBSTRING_INDEX() |	Return a substring from a string before the specified number of occurrences of the delimiter
SUBTIME() |	Subtract times
SUM() |	Return the sum
SYSDATE() |	Return the time at which the function executes
SYSTEM_USER() |	Synonym for USER()
TAN() |	Return the tangent of the argument
TIME() |	Extract the time portion of the expression passed
TIME_FORMAT() |	Format as time
TIME_TO_SEC() |	Return the argument converted to seconds
TIMEDIFF() |	Subtract time
* |	Multiplication operator
TIMESTAMP() |	With a single argument, this function returns the date or datetime expression; with two arguments, the sum of the arguments
TIMESTAMPADD() |	Add an interval to a datetime expression
TIMESTAMPDIFF() |	Subtract an interval from a datetime expression
TO_BASE64() |	Return the argument converted to a base-64 string
TO_DAYS() |	Return the date argument converted to days
TO_SECONDS() |	Return the date or datetime argument converted to seconds since Year 0
TRIM() |	Remove leading and trailing spaces
TRUNCATE() |	Truncate to specified number of decimal places
UCASE() |	Synonym for UPPER()
- |	Change the sign of the argument
UNCOMPRESS() |	Uncompress a string compressed
UNCOMPRESSED_LENGTH() |	Return the length of a string before compression
UNHEX() |	Return a string containing hex representation of a number
UNIX_TIMESTAMP() |	Return a Unix timestamp
UpdateXML() |	Return replaced XML fragment
UPPER() |	Convert to uppercase
USER() |	The user name and host name provided by the client
UTC_DATE() |	Return the current UTC date
UTC_TIME() |	Return the current UTC time
UTC_TIMESTAMP() |	Return the current UTC date and time
UUID() |	Return a Universal Unique Identifier (UUID)
UUID_SHORT() |	Return an integer-valued universal identifier
UUID_TO_BIN() |	Convert string UUID to binary
VALIDATE_PASSWORD_STRENGTH() |	Determine strength of password
VALUES() |	Define the values to be used during an INSERT
VAR_POP() |	Return the population standard variance
VAR_SAMP() |	Return the sample variance
VARIANCE() |	Return the population standard variance
VERSION() |	Return a string that indicates the MySQL server version
WAIT_FOR_EXECUTED_GTID_SET() |	Wait until the given GTIDs have executed on the slave.
WAIT_UNTIL_SQL_THREAD_AFTER_GTIDS() | (deprecated 8.0.18)	Deprecated. Use WAIT_FOR_EXECUTED_GTID_SET().
WEEK() |	Return the week number
WEEKDAY() |	Return the weekday index
WEEKOFYEAR() |	Return the calendar week of the date (1-53)
WEIGHT_STRING() |	Return the weight string for a string
XOR |	Logical XOR
YEAR() |	Return the year
YEARWEEK() |	Return the year and week