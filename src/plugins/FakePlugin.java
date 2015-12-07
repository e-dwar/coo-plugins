package plugins;

public class FakePlugin implements Plugin {

	public String transform(String text) {
		return null;
	}

	public String getLabel() {
		return "FakePlugin";
	}

}
