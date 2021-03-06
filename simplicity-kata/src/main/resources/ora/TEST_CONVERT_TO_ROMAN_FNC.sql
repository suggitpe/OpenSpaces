-- A simple test harness to test the convert_to_roman_fnc function.
-- Takes a number and the expected roman numeral.
-- Compares the expected value with the result
-- generated by the convert_to_roman_fnc and returns
-- 'PASS' or 'FAIL'.
CREATE OR REPLACE FUNCTION TEST_CONVERT_TO_ROMAN_FNC 
( num_in IN NUMBER,
  expected_roman_in IN VARCHAR2 )
RETURN VARCHAR2 AS 
  actual_roman VARCHAR2(64);
BEGIN
  actual_roman := convert_to_roman_fnc(num_in);
  
  IF actual_roman = expected_roman_in THEN
    RETURN 'PASS';
  ELSE
    RETURN 'FAIL: expected ''' || expected_roman_in || ''', actual ''' || actual_roman || '''';
  END IF;
END TEST_CONVERT_TO_ROMAN_FNC;