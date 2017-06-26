package pondthaitay.roompersistencelibrary.example

//@RunWith(AndroidJUnit4::class)
//class SimpleEntityReadWriteTest {
//    private UserDao mUserDao;
//    private TestDatabase mDb;
//
//    @Before
//    public void createDb()
//    {
//        Context context = InstrumentationRegistry . getTargetContext ();
//        mDb = Room.inMemoryDatabaseBuilder(context, TestDatabase.class).build();
//        mUserDao = mDb.getUserDao();
//    }
//
//    @After
//    public void closeDb() throws IOException
//    {
//        mDb.close();
//    }
//
//    @Test
//    public void writeUserAndReadInList() throws Exception
//    {
//        User user = TestUtil . createUser (3);
//        user.setName("george");
//        mUserDao.insert(user);
//        List<User> byName = mUserDao . findUsersByName ("george");
//        assertThat(byName.get(0), equalTo(user));
//    }
//}