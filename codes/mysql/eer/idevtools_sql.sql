select * from lookup_values_v;

select * from user_tag_t;

select * from tool_tag_t;

select LAST_INSERT_ID();

call proc_user_join('lis', 'sdf@23ss.com', 'lili');

select * from user_t where user_id = 67;

select count(1) from user_t where valid = 1;

select * from tool_dir_t where parent_dir_id = 5;

(SELECT d.download_id,
	    d.user_id,
        d.download_time,
        d.tool_id,
        t.tool_name,
        t.tool_version,
        t.code_name,
        t.description,
        t.website,
        t.download_links,
        t.usable,
        t.download_count,
        t.collect_count,
        t.last_update_time
   FROM downloads_t d JOIN tool_t t
	 ON d.tool_id = t.tool_id)
UNION
(SELECT d.download_id,
	    d.user_id,
        d.download_time,
        d.tool_id,
        t.tool_name,
        t.tool_version,
        t.code_name,
        t.description,
        t.website,
        t.download_links,
        t.usable,
        t.download_count,
        t.collect_count,
        t.last_update_time
   FROM downloads_t d JOIN tool_unusable_t t
     ON d.tool_id = t.tool_id);
     
SELECT * FROM downloads_tool_v;

(SELECT c.collect_id,
	    c.user_id,
        c.collect_time,
        c.tool_id,
        t.tool_name,
        t.tool_version,
        t.code_name,
        t.description,
        t.website,
        t.download_links,
        t.usable,
        t.download_count,
        t.collect_count,
        t.last_update_time
   FROM collections_t c JOIN tool_t t
	 ON c.tool_id = t.tool_id)
UNION
(SELECT c.collect_id,
	    c.user_id,
        c.collect_time,
        c.tool_id,
        t.tool_name,
        t.tool_version,
        t.code_name,
        t.description,
        t.website,
        t.download_links,
        t.usable,
        t.download_count,
        t.collect_count,
        t.last_update_time
   FROM collections_t c JOIN tool_unusable_t t
     ON c.tool_id = t.tool_id);

SELECT * FROM collections_tool_v;

insert into collections_t(user_id,tool_id,collect_time)
    select 1, 6, now()
    from DUAL
    where not exists
    (select 1
    from collections_t
    where user_id=1 and tool_id=6);

call proc_submit_suggestion(2, '？？？？', @rt);
select @rt;

SELECT REPLACE('${home} you?', '${home}', '中科大');

call proc_recommend_tool(2, 'Aapowersoft', 'https://www.apowersoft.cn/free-online-screen-recorder?tdsourcetag=s_pctim_aiomsg', '贼好用，很方便！', @rt);
SELECT @rt;

