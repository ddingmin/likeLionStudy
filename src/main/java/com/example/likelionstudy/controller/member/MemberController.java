package com.example.likelionstudy.controller.member;

import com.example.likelionstudy.dto.Member;
import com.example.likelionstudy.service.member.MemberService;
import com.example.likelionstudy.service.member.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * Controller 어노테이션을 통해 현재 class를 스프링 bean으로 등록합니다.
 * Spring에서 Controller의 역할을 부여합니다.
 * RequestMapping 어노테이션을 통해 해당 클래스는 members url을 통해 요청한 값들을 처리하도록 합니다.
 *  **/
@Controller
@RequestMapping("members")
public class MemberController {
    /*
     * final은 불변의 의미를 갖는다. 한 번 선언되면 이후 변경할 수 없는 성질을 갖는데,
     * class에서의 final은 상속할 수 없음을 의미하기도 한다.
     * memberService 또한 Spring Bean으로 한 번 생성된 이후 누군가가 memberService의 객체를 변경해서는 안된다.
     * 따라서 final을 통해 불변 class로 선언해준다.
     **/
    private final MemberService memberService;

    /*
     * (예시)
     * 이 것은 생성자입니다.
     * @Autowired라는 어노테이션은 MemberController 객체를 실행해야 할 때 필요한 의존성을 주입해달라고 선언하기 위해 명시하는 어노테이션이며, 생성자 주입 방식을 선언하고 있습니다.
     * MemberController의 필드를 MemberService 타입으로 선언하였지만, 생성자 paramer에는 MemberServiceImpl이 주입되게 함으로써 느슨한 결합(Loosen Coupling)을 구현하였습니다.
     * @참고 : 실제로는 MemberController 생성자의 파라미터에 MemberServiceImpl이 아니라 MemberService로 쓰여있어도 스프링이 알아서 구현체 클래스의 인스턴스 (MemberServiceImpl memberserviceimpl)를 넣어주게 됩니다.
     *       즉, public MemberController(MemberService memberService) {this.memberService = memberService;} 와 같이 작성해도 에러가 없고, 이게 사실 정석입니다.
     *       아래처럼 작성해 둔 이유는, 실제로는 아래와 같이 동작한다는 것을 여러분 눈으로 먼저 보길 바랐던 제 마음이었습니다.
     *       지금, MemberController의 필드가 MemberService 타입의 데이터인데, 생성자로 주입되는 것은 MemberServiceImpl 타입이라는 것을 충분히 음미하시길 바랍니다.
     **/
    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberService = memberServiceImpl;
    }

    /*
     * GetMapping 어노테이션으로 해당 URL (members/new)에 Get 요청을 받았을 때 수행합니다.
     * model은 컨트롤러에서 생성된 데이터를 view로 전달하는 역할을 합니다.
     * Member 객체를 통해 새롭게 생성하고, 생성된 객체를 view로 전달합니다.
     **/
    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new Member());
        return "members/createMemberForm";
    }

    /*
     * PostMapping 어노테이션으로 해당 URL (members/new)에 Post 요청을 받았을 때 수행합니다.
     * 요청받은 member 객체를 받아 db에 memberService의 save 메소드를 통해 db에 저장합니다.
     * 저장 후 페이지를 / (members)로 리다이엑트 해준다.
     **/
    @PostMapping("new")
    public String create(Member member) {
        memberService.save(member);
        return "redirect:/";
    }

    /*
     * GetMapping 어노테이션으로 해당 URL (members)에 Get 요청을 받았을 때 수행합니다.
     * memberServie의 findAll 메소드를 통해 맴버의 정보를 모두 가져옵니다.
     * 가져온 memberList를 model.addAttribute를 통해 View로 전달합니다.
     * 이후 members/memverList의 html 파일을 찾아 전달합니다.
     **/
    @GetMapping("")
    public String findAll(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "members/memberList";
    }
}